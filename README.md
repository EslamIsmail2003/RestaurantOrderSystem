# Restaurant Order System

## Overview
A CLI-based restaurant management system built in Java that communicates
with a cloud PostgreSQL database. Supports customer ordering, admin
control, and business analytics.

## Tech Stack
- Java 17
- Maven
- PostgreSQL (hosted on Neon)
- Logback (logging)
- Lombok (boilerplate reduction)

## Setup
1. Clone the repo:
   git clone https://github.com/EslamIsmail2003/RestaurantOrderSystem

2. Request application.properties from the developer and place it in:
   src/main/resources/application.properties

3. Run Main.java

No local database setup required — connects to cloud database automatically.

## Features
**Customer Portal:**
- Register a new account
- Browse menu by category and place orders
- View order history by email
- Cancel pending orders
