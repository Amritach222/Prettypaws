<?php
	if($_POST["dogid"]!=null && $_POST["userid"]!=null){
		$userid=$_POST["userid"];
		$dogid=$_POST["dogid"];
		require_once("connection.php");
		$sql="INSERT INTO likedislke(dog_id,user_id) VALUES('$dogid','$userid')";
		$run=mysqli_query($con,$sql);
		$result=array();
		if($run){
			$result['success']="1";

		}
		else{
			$result['success']="0";
		}
		echo json_encode($result);
	}
	else{
		echo "Probelm with sending data to the server..";
	}
?>