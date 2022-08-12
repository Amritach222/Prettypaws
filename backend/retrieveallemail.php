<?php
$resetEmail=$_POST['email'];
include('connection.php');
$query="SELECT * FROM user_table WHERE email='$resetEmail'";
$result=array();
$run=mysqli_query($con,$query);
if (mysqli_num_rows($run)>0) {
	$result['success']="1";
	# code...
}
else{
	$result['success']="0";

}
echo json_encode($result);
mysqli_close($con);
?>