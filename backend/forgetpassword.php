<?php
session_start();
if(isset($_SESSION['admin_email']))
{

}
else
{
    header("location:index.php");
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Password Change Field</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<div class="row">
<div class="col-3"></div>
<div class="col-6">
<h5 class="head_line">Enter Security Code </h5>
<hr style="color:rgb(230, 225, 225)">
<span class="message " >Please check your email for text message with verification code. Your code is 5 characters long.</span>
<form action="" method="post">
<input type="number" name="vcode" class="input-code"placeholder="Enter code" >
<?php
  if (isset($_POST['enter']))
  {
    $con= mysqli_connect("localhost","root","","prettypaws");
    $vcode=$_POST['vcode'];
    $codelen=strlen($vcode);
    $dbcode=mysqli_query($con,"select * from login where id='1'");
    while($rows=mysqli_fetch_array($dbcode))
    {
      $code=$rows['code'];
    }
    if($codelen==5)
    {
      if($vcode==$code)
      {
        header("location:changepassword.php");
      }
      else{
        echo " <br><br><div class='alert alert-danger'>
        <strong>Entered code is incorrect.Please Enter the correct verification code.</strong> 
      </div>";
      }
    }
    else{
      echo " <br><br><div class='alert alert-danger'>
      <strong>Code must be of 5 digit.</strong> 
    </div>";
    }
    mysqli_close($con);
    $_SESSION['code']=$code;
    unset($_SESSION['admin_email']);


  }
  if(isset($_POST['cancel']))
  {
    header("location:index.php");
  }
?>
<hr style="color:rgb(230, 225, 225)">
<a href="index.php?" style="font-family:TimesNewRoman">Didn't get a code?</a>
<input type="submit" name="enter" class="btn btn-primary" value="Continue"> 
<input type="submit" name="cancel"  value="Cancel" class="btn btn-cancel "> 

</form>
<br>
</div>
<div class="col-3"></div>
</div>
</div>
<style>
.navbar{
  height:100px;
}
body{
 background:rgb(230, 225, 225);
}
.col-6
{
  margin-top:100px;
  border-radius:7px ;
  background:white;
}
.head_line{
  padding-top:10px;
  font-family:TimesNewRoman;
  font-weight:bold;
}
.message{
  font-size:14px;
  font-family:TimesNewRoman;
}
.btn{
  float:right;
  font-family:TimesNewRoman;

}
.btn-cancel
{
  background:rgb(230, 225, 225);
  margin-right:10px;
  
}
.input-code{
  margin-top:10px;
  height:40px;
  width:200px;
  font-size:20px;
  font-Weight:bold;
}

</style>
</body>
</html>
