<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin DashBoard</title>
     <!-- Linking style.css file -->
    <link rel="stylesheet" href="css/style.css">
    
    <!-- Font awesome for icons -->
  <link rel="stylesheet" href="css/all.min.css">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@600;700&display=swap" rel="stylesheet">
</head>
<body>
    <section id="sidebar">
        <div class="sidebar-logo">
       <h2><i class="fas fa-dog"></i><span>PrettyPaws</span></h2>
        </div>
        <div class="sidebar-menu">
            <ul>
                <li><a href="#"><i class="fab fa-dashcube"></i><span>DashBoard</span></a></li>
                <li><a href="#"><i class="fas fa-user"></i><span>Account</span></a></li>
                <li><a href="#"><i class="fas fa-bell"></i><span>Updates</span></a></li>
                <li><a href="#"><i class="fas fa-coins"></i><span>Donation</span></a></li>
                <li><a href="#"><i class="fas fa-skiing"></i><span>Activity</span></a></li>
                <li><a href="#"><i class="fas fa-sign-out-alt"></i><span>Logout</span></a></li>
            </ul>
        </div>
    
    </section>
    <section id="maincontent">
        <header class="main-header">
            <div class="header-part" id="part1">
                <h2><i class="fa fa-bars"></i> Dashboard</h2>
            </div>
            <div class="header-part" id="part2">
                <input class="search-bar" type="text" placeholder="Search here.."/>
                <i class="fa fa-search"></i>
            </div>
            <div class="header-part" id="part3">
                <h2><i class="fas fa-user"></i></h2>
                <p>Admin</p>
            </div>
        </header>

        <footer class="main-footer">
            <div class="footer-1">
                <P><a href="privacy.html">Privacy Policy</a></P>
            </div>
            <div class="footer-2">
                <P><a href="#">Terms and Conditions</a></P>
            </div>
            <div class="footer-3">
             <p>Copyright &copy;2021 PrettyPaws. Designed by CodeSastra.org</p>
         </div>
        </footer>
        <div class="main-body">
          <div class="other-details">
          <h2>For Account,updates and donation part</h2>
          </div>
          <div class="dog-details">
              
            <?php

            require 'DogAdoption/fetch.php';
        
            if($count > 0)
            {
            while($row= mysqli_fetch_array($result))
            {
                ?>  
                <div class="demo-dog">
                    <div class="demo-dog-details">
                        <img src="" alt="No image">
                        <P>Name:<?php  echo $row['name']; ?></P>
                        <p>Age:<?php  echo $row['age']; ?></p>
                        <P>Description:<?php  echo $row['descr']; ?></P>
                    </div>
                </div>
                <?php
               
            }
            }
            ?>
          
            <!-- <div class="demo-dog">

            </div>
            <div class="demo-dog">

            </div> -->
            
        </div>
        </div>

    </section>
</body>
</html>