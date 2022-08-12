<?php
	require_once('connection.php');
	$select="SELECT * from approved_dogs";
	$result=array();
	$result['dog']=array();
	$run_select=mysqli_query($con,$select);
	while ($row=mysqli_fetch_array($run_select)) {
		$index['id']=$row[0];
		$index['name']=$row[1];
		$index['address']=$row[2];
		$index['image']=$row[3];
		$index['age']=$row[4];
		$index['gender']=$row[5];
		$index['description']=$row[6];
		$index['breed']=$row[7];
		$index['user_id']=$row[8];

		array_push($result['dog'],$index);

	# code...
	}
	$result['success']="1";
	echo json_encode($result);
	mysqli_close($con);
?>