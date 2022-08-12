<?php
if ($_POST['userimage']!=null && $_POST['email']!=null && $_POST['password']!=null) {
	# code...

	$target_dir="userImage/";
	$image=$_POST['userimage'];
	$email=$_POST['email'];
	$password=md5($_POST['password']);
	$imageStore=rand()."_".time().".jpeg";
	require_once('connection.php');
	$query="UPDATE user_table SET imagename='$imageStore' WHERE email='$email' and password='$password'";
	 $result_message=array();
	$run=mysqli_query($con,$query);
	if ($run) {
		$target_dir=$target_dir."/".$imageStore;
		file_put_contents($target_dir,base64_decode($image));
		 $result_message['success']="1";
		
		# code...
	}
	else{
		 $result_message['success']="0";

	}
	echo json_encode($result_message);
	mysqli_close($con);
}
else{
	echo "Please pass the value of image";
}
?>