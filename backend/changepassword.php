<?php
session_start();
if(isset($_SESSION['code']))
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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
   
    <title>Change Password Section</title>
</head>
<body>
<?php
$host='localhost';
$name='root';
$pass='';
$dbname='product_db';

$connect=mysqli_connect($host,$name,$pass,$dbname);

?>
<div class="container boxform">
<div class="col-12">
<p style="font-weight:bold"></p>
</div>
<form action="" method="post" name="settingform">
<br>
  
  <div class="form-group">
  <h3>Change Password</h3>
  <hr>
  <div class="form-group">
    <label for="exampleInputPassword1"><strong>New Password</strong></label>
    <input type="password" name="newpassword" class="form-control" id="exampleInputPassword1" required placeholder="New Password">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1"><strong>Confirm Password</strong></label>
    <input type="password" name="confirmpassword" class="form-control" id="exampleInputPassword1" required placeholder="Confirm Password">
  </div>
  <button type="submit" style="float:right" name="changepassword" class="btn btn-primary">Save Changes</button>
  <br>
  <br>
  <?php
  if(isset($_POST['changepassword']))
  {
      
      $newpassword=md5($_POST['newpassword']);
      $confirmpassword=md5($_POST['confirmpassword']);
          if($newpassword==$confirmpassword)
          {
              mysqli_query($connect,"update login set password='$confirmpassword'");
              echo " <br> <div class='alert alert-success'>
        <strong>Successfully Changed !! <a href='index.php'>Click Here</a> to login</strong> 
      </div>";
          }
          else{
            echo " <br> <div class='alert alert-danger'>
            <strong>password does not match </strong> 
          </div>";
          }
          unset($_SESSION['code']);

      }
  
  ?>
</form>
</div>

<style>
.boxform
{
    margin-top:100px;
  border-radius:7px ;
  background:white;
  font-family:TimesNewRoman;
}
body{
    background:rgb(230, 225, 225); 
}
</style>

    
</body>

</html>