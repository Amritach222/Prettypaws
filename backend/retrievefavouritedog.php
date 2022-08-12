<?php
	if($_POST["userid"]!=null){
		$userid=$_POST["userid"];
		require_once("connection.php");
		$sql="SELECT * FROM likedislke WHERE user_id='$userid'";
		$run=mysqli_query($con,$sql);
		$result=array();
		$result['favourite']=array();
		while($row=mysqli_fetch_array($run)){
			$dogid=$row[0];
			$fsql="SELECT * FROM approved_dogs WHERE id='$dogid'";
			$query_run=mysqli_query($con,$fsql);
			while($frow=mysqli_fetch_array($query_run)){
					$index['id']=$frow[0];
					$index['name']=$frow[1];
					$index['address']=$frow[2];
					$index['image']=$frow[3];
					$index['age']=$frow[4];
					$index['gender']=$frow[5];
					$index['description']=$frow[6];
					$index['breed']=$frow[7];
					$index['user_id']=$frow[8];
				array_push($result['favourite'],$index);
			}

		}
		$result['success']="1";
		echo json_encode($result);
		mysqli_close($con);
	}
	else{
		echo "Probelm with sending data to server..";
	}
?>