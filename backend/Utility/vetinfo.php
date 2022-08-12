<?php
session_start();
if(isset($_SESSION['username']) && isset($_SESSION['password']))
{

}
else{
    header("location:https://localhost/dogFinderApp");
}
?><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../css/all.min.css">
 
    <?php
include 'bootstrap.html';
?>
    
</head>
<body>
    <?php
    include('navigationbar.php');

    ?>
     <div  style="text-align:center; margin-top:20px"> 
        <h1>Veterinarian Information</h1>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
<i class="fas fa-plus"></i>
</button>

    </div>
    <!-- Button trigger modal -->
<!-- Modal -->
<form action='' method='post'>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Veterinary Information</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <div class="form-group">
                            <label>Name:</label>
                            <input type="name" class="form-control" id="name" name="name">
                        </div>
                        <div class="form-group">
                            <label>Address:</label>
                            <input type="address" class="form-control" id="adddres" name="address">
                        </div>
                        
                        <div class="form-group">
                            <label>Phone No:</label>
                            <input type="tel" class="form-control" id="phone" name="phoneno">
                        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" name='save_btn' class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
</form>
<?php
if(isset($_POST['save_btn'])){
    $name=$_POST['name'];
    $address=$_POST['address'];
    $phoneno=$_POST['phoneno'];

    require '../connection.php';

    $sql="INSERT INTO veterinary_table(name,address,contact) VALUES('$name','$address','$phoneno')";
    $run=mysqli_query($con,$sql);
    if ($run)
    {
    echo "<script>window.location.replace('vetinfo.php');
    alert('successfully inserted');
    </script> ";

    }
    else{
        echo "<sccript>alert('unsuccess')</sccript>";
    }
}

?>
    
    <div style="overflow-y:scroll;  width:50%; margin: 40px auto; border:1px solid grey; border-radius:3px">
    <table class="table table-hover">
    <thead>
      <tr>
        <th>S.N</th>
        <th>Name</th>
        <th>Address</th>
        <th>Phone No</th>
      </tr>
    </thead>
        
        <?php 
        $sn=1;
        require '../connection.php';
        $sql="SELECT * FROM veterinary_table";
        $result=mysqli_query($con,$sql)  or die(mysqli_error($con));
        $count=mysqli_num_rows($result);
        if($count >0)
        {
            while($row=mysqli_fetch_array($result))
            {
        ?>
    <tbody>
      <tr>
        <td><?php echo $sn++; ?></td>
        <td><?php echo $row['name']; ?></td>
        <td><?php echo $row['address'] ; ?></td>
        <td><?php echo $row['contact'];    }
}
?></td>
      </tr>
    </tbody>

</table>
</div>
<?php
include('footer.php');

?>

</body>
</html>