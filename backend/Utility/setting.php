<?php
session_start();
if(isset($_SESSION['username']) && isset($_SESSION['password']))
{

}
else{
    header("location:https://localhost/dogFinderApp");
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
   
    <title>Setting</title>
    <?php
    include('bootstrap.html');
    ?>
</head>
<body>
<?php
include "navigationbar.php";

?>
<div class="container boxform">
<div class="col-12">
<p><img src="https://img.icons8.com/material-sharp/20/000000/settings.png"> Setting</p>
</div>
<form action="" method="post" name="settingform">
<br>
  <h3>Change Username</h3>
  <hr>
  <div class="form-group">
    <label for="exampleInputPassword1"><strong>Username</strong></label>
    <input type="text" name="username" class="form-control" id="exampleInputPassword1"  placeholder="Admin Name Here">
    <label for="exampleInputPassword1"><strong>Email</strong></label>
    <input type="email" name="useremail" class="form-control" id="exampleInputPassword1"  placeholder="Admin Email Here">
  </div>
  <button type="submit" name="saveusername"class="btn btn-success">Save Changes</button>
  <br>
  <?php
   include('../connection.php');
  if(isset($_POST['saveusername']))
  {
    $username=$_POST['username'];
    $useremail=$_POST['useremail'];
      $selectsql="select * from login";
      $datas=mysqli_query($con,$selectsql);
      while($rows=mysqli_fetch_array($datas))
      {
          $dbid=$rows['id'];
          $dbusername=$rows['username'];
          $dbuseremail=$rows['admin_email'];
          $dbpassword=$rows['password'];
      }
      if($username==null)
      {
        echo " <br> <div class='alert alert-danger'>
          <strong>Username Can't be Null</strong> 
        </div>";
      }
      else
      {
   mysqli_query($con, "update login set username ='$username',admin_email='$useremail' where id='1'");
   echo " <br> <div class='alert alert-success'>
        <strong>Successfully changed !!</strong> 
      </div>";
      } 
  }
  ?>
  <br>
  <h3>Change Password</h3>
  <hr>
  <div class="form-group">
    <label for="exampleInputPassword1"><strong>Current Password</strong></label>
    <input type="password" name="currentpassword" class="form-control" id="exampleInputPassword1"  placeholder="Current Password">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1"><strong>New Password</strong></label>
    <input type="password" name="newpassword" class="form-control" id="exampleInputPassword1" placeholder="New Password">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1"><strong>Confirm Password</strong></label>
    <input type="password" name="confirmpassword" class="form-control" id="exampleInputPassword1" placeholder="Confirm Password">
  </div>
  <button type="submit" name="savepassword" class="btn btn-primary">Save Changes</button>
  <br>
  <?php
  if(isset($_POST['savepassword']))
  {
      $cupassword=md5($_POST['currentpassword']);
      $newpassword=md5($_POST['newpassword']);
      $confirmpassword=md5($_POST['confirmpassword']);
      $selectsql="select * from login";
      $datas=mysqli_query($con,$selectsql);
      while($rows=mysqli_fetch_array($datas))
      {
          $dbid=$rows['id'];
          $dbusername=$rows['username'];
          $dbpassword=$rows['password'];
      }
      if($dbpassword!=$cupassword)
      {
        echo " <br> <div class='alert alert-danger'>
        <strong>Please enter correct current password</strong> 
      </div>";
        
      }
      else{
          if($newpassword==$confirmpassword)
          {
              mysqli_query($con,"update login set password='$confirmpassword'");
              echo " <br> <div class='alert alert-success'>
        <strong>Successfully Changed !!</strong> 
      </div>";
          }
          else{
            echo " <br> <div class='alert alert-danger'>
            <strong>password does not match </strong> 
          </div>";
          }
      }

  }
  
  ?>
</form>
</div>

<style>
.boxform
{
   padding:10px; 
}
.col-12{
    height:60px;
    background-color:rgb(242, 245, 235);
    border-top-left-radius:20px;
    border-top-right-radius:20px;
    padding-top:17px;
}
</style>
<?php
include "footer.php";
?>

    
</body>

</html>