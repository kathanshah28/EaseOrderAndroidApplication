# EaseOrder Android Application

An Android food ordering application built with Java and Android Studio. The app allows users to browse menu items, add products to the cart, place orders, and view their order history through REST API integration.

## Overview

This project was developed as a mobile ordering app prototype and includes:
- Food menu browsing
- Category-based item selection
- Cart management
- Order placement flow
- Order history tracking
- Login / landing screen flow
- API-driven data retrieval using Retrofit

## Features

- Browse food items and categories
- View food details and images
- Add/remove items from cart
- Track order summary and total cost
- Place and review orders
- View past orders
- Smooth navigation between screens
- Material UI styling and Android app components

## Tech Stack

- Java
- Android SDK
- AndroidX / Material Components
- Retrofit 2
- Gson
- Picasso
- Gradle Kotlin DSL

## Project Structure

```text
EaseOrderAndroidApplication
├── app
│   ├── src
│   │   ├── androidTest
│   │   ├── main
│   │   │   ├── java/com/example/retrofit_apigetpractice
│   │   │   ├── res
│   │   │   └── AndroidManifest.xml
│   │   └── test
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
├── .gitignore
└── README.md
```

## Main App Components

The app includes classes related to:

* `MainActivity`
* `LandingPage`
* `HomeFragment`
* `cartFragment`
* `orderHistoryFragment`
* Food and category models
* Retrofit API interfaces
* RecyclerView adapters for menu items and orders

## Prerequisites

Before running the project, make sure you have:

* Android Studio
* JDK 17 or later
* Android SDK 34
* Internet connection for API access

## Setup Instructions

1. Clone the repository:
```bash
git clone https://github.com/kathanshah28/EaseOrderAndroidApplication.git
```
2. Open the project in Android Studio.
3. Let Gradle sync the project dependencies.
4. Ensure the required Android SDK packages are installed.
5. Run the app on an emulator or a physical Android device.

## API Integration

This app uses Retrofit to fetch and submit data from a backend API. The project includes model classes and interface definitions for:

* Food items
* Categories
* Cart actions
* Order history
* User login flow
