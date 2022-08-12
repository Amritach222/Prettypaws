<?php
    
    $name=$_POST['name'];    
    $email=$_POST['email'];
    $contact_number=$_POST['contact_number'];
    $address=$_POST['address'];
    $password=$_POST['password'];
    $pass=md5($password);
    require_once 'connection.php';
        $query="INSERT INTO user_table(name,email,contact_number,address,password) VALUES('$name','$email','$contact_number','$address','$pass')";
        $result=mysqli_query($con,$query);
        $result_message=array();
        if($result){
            $result_message['success']="1";
        
        }
        else{
            $result_message['success']="0";
        }
        echo json_encode($result_message);
        // Connection close  
     mysqli_close($con); 
?>