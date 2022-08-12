<?php
	$userid=$_POST["userid"];
	$q1=$_POST["q1"];
	$q2=$_POST["q2"];
	$q3=$_POST["q3"];
	$q4=$_POST["q4"];
	$q5=$_POST["q5"];
	$q6=$_POST["q6"];
	$q7=$_POST["q7"];
	$q8=$_POST["q8"];
	$q9=$_POST["q9"];
	$q10=$_POST["q10"];
	$dogid=$_POST["dogid"];
	include_once('connection.php');
	$query="INSERT INTO adoption(user_id,q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,dog_id) VALUES('$userid','$q1','$q2','$q3','$q4','$q5','$q6','$q7','$q8','$q9','$q10','$dogid')";
	$result=mysqli_query($con,$query);
	$result_msg=array();
	if($result){
	 $result_msg['success']="1";
	}
	else{
		$result_msg['success']="0";
	}
	echo json_encode($result_msg);
	mysqli_close($con);

?>