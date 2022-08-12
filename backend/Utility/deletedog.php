<?php
include '../connection.php';
if(isset($_GET['id'])){
    $id=$_GET['id'];
    $query="DELETE FROM dog_table WHERE id='$id'";
    $run=mysqli_query($con,$query);
    if($run){
        header("location:../home.php");

    }
    else{
        echo "Failed to delete.";
    }
    
}else{
    echo "Something went wrong";
}
?>