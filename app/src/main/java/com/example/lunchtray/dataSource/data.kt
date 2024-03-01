package com.example.lunchtray.dataSource

import com.example.lunchtray.Model.MenuItem.AccompanimentItem
import com.example.lunchtray.Model.MenuItem.SideDishItem
import com.example.lunchtray.Model.MenuItem.EntreeItem

object DataSource {
    val entreeItems = listOf(
        EntreeItem(
            "Cauliflower",
            "Whole cauliflower ,brinded, roasted and deepfried",
            25.4
        ),
        EntreeItem(
            name = "Three Bean Chili",
            description
            = "Black beans, red beans, kidney beans, slow cooked, topped with onion",
            price = 4.00,
        ),
        EntreeItem(
            name = "Mushroom Pasta",
            description = "Penne pasta, mushrooms, basil, with plum tomatoes cooked in garlic " +
                    "and olive oil",
            price = 5.50,
        ),
        EntreeItem(
            name = "Spicy Black Bean Skillet",
            description = "Seasonal vegetables, black beans, house spice blend, served with " +
                    "avocado and quick pickled onions",
            price = 5.50,
        )

    )
    val sideDishItems = listOf(
        SideDishItem(
            name = "Summer Salad",
            description = "Heirloom tomatoes, butter lettuce, peaches, avocado, balsamic dressing",
            price = 2.50,
        ),
        SideDishItem(
            name = "Butternut Squash Soup",
            description = "Roasted butternut squash, roasted peppers, chili oil",
            price = 3.00,
        ),
        SideDishItem(
            name = "Spicy Potatoes",
            description = "Marble potatoes, roasted, and fried in house spice blend",
            price = 2.00,
        ),
        SideDishItem(
            name = "Coconut Rice",
            description = "Rice, coconut milk, lime, and sugar",
            price = 1.50,
        )
    )
    val accompanimentMenuItem = listOf(
        AccompanimentItem(
            name = "Lunch Roll",
            description = "Fresh baked roll made in house",
            price = 0.50,
        ),
        AccompanimentItem(
            name = "Mixed Berries",
            description = "Strawberries, blueberries, raspberries, and huckleberries",
            price = 1.00,
        ),
       AccompanimentItem(
            name = "Pickled Veggies",
            description = "Pickled cucumbers and carrots, made in house",
            price = 0.50,
        )
    )
}