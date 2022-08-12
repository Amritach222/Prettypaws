<?php
// if(isset($_POST['email'] && $_POST['password'])){
	$email=$_POST['email'];
	$password=md5($_POST['password']);
	require_once('connection.php');
	 $query="SELECT * FROM user_table WHERE email='$email' and password='$password'";
	 $result=array();
	 $result['data']=array();
	 $query_run=mysqli_query($con,$query);
	 while($row=mysqli_fetch_array($query_run)){
	 	$index['id']=$row['0'];
	 	$index['name']=$row['1'];
	 	$index['email']=$row['2'];
	 	$index['contact_number']=$row['3'];
	 	$index['address']=$row['4'];
	 	$index['imagename']=$row['6'];
	 	array_push($result['data'],$index);

	 }
	 $result['success']="1";
	 echo json_encode($result);
	 mysqli_close($con);

// }
// else{
// 	echo "send data failed!";
// }
?>