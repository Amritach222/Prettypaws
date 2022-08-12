<?php
    
    $email=$_POST['email'];
    $password=$_POST['password'];
    $pass=md5($password);
    require_once 'connection.php';
    $query="SELECT * FROM user_table WHERE email='$email' and password='$pass'";
    $query_run=mysqli_query($con,$query);
    $result=array();
    $row = mysqli_num_rows($query_run);
    if($row>0){
  $result["success"]="1";


  }
  else{
  $result["success"]="0";
  }
    echo json_encode($result);
     // Connection close  
     mysqli_close($con); 
?>