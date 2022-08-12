<?php
	$dogname=$_POST['dogname'];
	$dogage=$_POST['dogage'];
	$doggender=$_POST['doggender'];
	$dogimage=$_POST['dogimage'];
	$dogbreed=$_POST['dogbreed'];
	$doglocation=$_POST['doglocation'];
	$dogmessage=$_POST['dogmessage'];
	$userid=$_POST['userid'];
	$target_dir="dogImages/";
	$imageStore=rand()."_".time().".jpeg";
	require_once('connection.php');
	$query="INSERT INTO dog_table(dog_name,dog_address,dog_picture,dog_age,dog_gender,description,dog_breed,user_id) VALUES('$dogname','$doglocation','$imageStore','$dogage','$doggender','$dogmessage','$dogbreed','$userid')";
	$result_message=array();
	$run=mysqli_query($con,$query);
	if ($run) {
		$target_dir=$target_dir."/".$imageStore;
		file_put_contents($target_dir,base64_decode($dogimage));
		$result_message['success']="1";
		# code...
	}
	else{
		$result_message['success']="0";
	}
	echo json_encode($result_message);
	mysqli_close($con)
?>