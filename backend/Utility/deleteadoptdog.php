<?php
include('../connection.php');
if(isset($_GET['u_id']) && isset($_GET['d_id'])){
    $dogid=$_GET['d_id'];
    $userid=$_GET['u_id'];
    $query="DELETE FROM adoption WHERE user_id='$userid' and dog_id='$dogid'";
    $run_query= mysqli_query($con,$query);
    if($run_query){
        echo '<script>
        window.location.replace("adopting_dogs.php");
        alert("Successfully Deleted.");
        </script>';
    }
    else{
        echo "<h1>ERROR 404!!!<br> Occured.</h1>";
    }
}
else{
    echo "Unable to get data.";
}

?>
