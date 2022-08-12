<?php
	if($_POST["dogid"]!=null && $_POST["userid"]!=null){
		$userid=$_POST["userid"];
		$dogid=$_POST["dogid"];
		require_once("connection.php");
		$sql="DELETE FROM likedislke WHERE user_id='$userid' and dog_id='$dogid'" ;
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