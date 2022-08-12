<?php
session_start();
if(isset($_SESSION['username']) && isset($_SESSION['password']))
{

}
else{
    header("location:https://localhost/dogFinderApp");
}
?><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <?php
    include ('Utility/bootstrap.html');

    ?>
    <title>Dashboard</title>
</head>
<body>
  <?php
  include 'Utility/navigationbar.php';

  ?>
  
  <div class="row" style="margin:5pt;">
  <div class="col-sm-4 sticky" id="left" style='margin-top:30pt;'>
  <?php
include 'Utility/dashboard_body.html';
?>
  </div>   
  <div class="col-sm-8" id="right" style='overflow-y: scroll;height:500px;'>
  <h2 style="font-family:TimesNewRoman; text-align:center;">Dogs Added by User</h2>
  <?php
      include 'Utility/dashboard_addded_dog.php';
      ?>
  </div>
  </div>
  <div class="footer">
  <?php
  include 'Utility/footer.php';
  
  ?>
  </div>
</body>
</html>