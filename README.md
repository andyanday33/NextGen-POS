# NextGen-POS
## This project implements a NextGen POS design
- Classes and relationships' design matches GoF Patterns 80/100
- Includes handwritten UI (If you wanna change something you must add it to code itself, you can't use drag-drop system that your IDE offers)
- Includes tax-calculating (general and by item) and applying discount with different types of strategies.
## CustomerDiscounts.txt
### This txt holds discounts for vip customers
- Day,Discount_Amount => Format on every line which implies DISCOUNT_AMOUNT% amount of discount will be applied on DAY for vip customers.

## Customers.txt
### This txt holds SSN's of vip customers

## Item_discounts.txt
### This txt holds item based discounts
- Item_name,Discount_Amount => Format on every line which implies DISCOUNT_AMOUNT% amount of discount will be applied on ITEM_NAME.

## Descriptions.txt
### This txt holds id, type, price and name for each item
- Item_id,Item_type,Price,Item_name => Format on every line. Type = { 1:Non-Alcoholic Beverage, 2:Alcoholic Beverage, 3:Food}
- Item_id must be unique.
