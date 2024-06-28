# EasyShop
Backend API for EasyShop e-commerce application built with Spring Boot and MySQL.



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
