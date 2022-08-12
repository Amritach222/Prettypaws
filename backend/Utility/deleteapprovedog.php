<?php
include '../connection.php';
if(isset($_GET['id'])){
    $id=$_GET['id'];
    $query="DELETE FROM approved_dogs WHERE id='$id'";
    $run=mysqli_query($con,$query);
    if($run){
        echo '<script>
        window.location.replace("approved_detail.php");
        alert("successfully deleted");
        </script>';

    }
    else{
        echo "Failed to delete.";
    }
    
}else{
    echo "Something went wrong";
}
?>