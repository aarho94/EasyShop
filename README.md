# EasyShop
Backend API for EasyShop e-commerce application built with Spring Boot and MySQL.

<img width="1614" alt="Screenshot 2024-06-28 at 12 51 43 PM" src="https://github.com/aarho94/EasyShop/assets/166449365/97c9ea67-95a9-40c9-9a1a-a66a77455c06">

**User Stories and Tasks**

**Bugs**

**Bug 1: Search/Filter Functionality**

Title: Fix search/filter functionality for products.

Description: The search functionality is returning incorrect results. Implement unit tests to identify and fix the issue.

Tasks:

Write unit tests for product search with various parameters.

- Debug and identify the issue in the search logic.

- Implement fixes and verify with unit tests.

**Bug 2: Duplicate Products Issue**

Title: Fix product update functionality to prevent duplicate entries.

Description: Updating products is causing duplicates instead of modifying existing entries.

Tasks:

- Investigate the product update logic.
- Ensure the update functionality modifies existing entries instead of creating new ones.

**New Features**

**Feature 1: Categories Management**

Title: Implement CategoriesController to manage product categories.

Description: Add functionality to handle CRUD operations for product categories. Only administrators should have the ability to add, update, or delete categories.

Tasks:

- Implement GET endpoints for retrieving categories.
- Implement POST endpoint for adding a new category.
- Implement PUT endpoint for updating an existing category.
- Implement DELETE endpoint for removing a category.
- Add authentication and authorization to ensure only admins can modify categories.

**Home Screen**

<img width="1907" alt="Screenshot 2024-06-28 at 7 51 42 AM" src="https://github.com/aarho94/EasyShop/assets/166449365/786c038b-8125-42a2-bc13-5bb60b7e8232">

**Categories Filters**

<img width="339" alt="Screenshot 2024-06-28 at 7 54 10 AM" src="https://github.com/aarho94/EasyShop/assets/166449365/d6e1189b-fc33-445c-b57c-524605c569f4">

**Price Filter**

<img width="872" alt="Screenshot 2024-06-28 at 7 56 12 AM" src="https://github.com/aarho94/EasyShop/assets/166449365/3bbedc25-41ac-4416-a2b0-7ef97df72eaf">

**Postman Tests for Bug fixes**

<img width="796" alt="Screenshot 2024-06-28 at 12 55 43 PM" src="https://github.com/aarho94/EasyShop/assets/166449365/1aec8d9f-7570-43e2-bd59-518f0253ab05">

I really enjoyed setting up the CategoriesController class, and figuring out what data to pull in to make it work!

<img width="813" alt="Screenshot 2024-06-28 at 12 56 55 PM" src="https://github.com/aarho94/EasyShop/assets/166449365/112f746a-0241-48e8-b4bf-4f45f9101468">

Debugging the filters in the product dao was also really satisfying when all was finished!

<img width="906" alt="Screenshot 2024-06-28 at 12 59 00 PM" src="https://github.com/aarho94/EasyShop/assets/166449365/a8e7ed8f-f27a-454f-9353-91e28364ce32">
