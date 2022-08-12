<?php
$id=$_POST["id"];
include 'connection.php';
$query="SELECT * FROM update_table WHERE user_id='$id'";
$result=array();
$result['message']=array();
$run_select=mysqli_query($con,$query);
while ($row=mysqli_fetch_array($run_select)) {
	$index['id']=$row[0];
	$index['message']=$row[1];


	array_push($result['message'],$index);
}
$result['success']="1";
echo json_encode($result);
mysqli_close($con);
?>