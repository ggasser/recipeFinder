RecipeFinder
============
Given a list of items in the fridge (presented as a csv list), and a collection of recipes (a collection of JSON formatted recipes), produce a recommendation for what to cook tonight.


Usage:

./bin/recipeFinder.sh &lt;fridge.csv&gt; &lt;recipes.json&gt;

## Fridge Format - CVS
```
Format: item, amount, unit, use-by 
Where:
    ￼Item (string) = the name of the ingredient – e.g. egg)
    Amount (int) = the amount
    Unit (enum) = the unit of measure, values;
          of (for individual items; eggs, bananas etc) 
          grams
          ml (milliliters)
          slices
    Use-By (date) = the use by date of the ingredient (dd/mm/yy)
```
Fridge Example:
```csv
bread,10,slices,25/12/2014
cheese,10,slices,25/12/2014
butter,250,grams,25/12/2014
peanut butter,250,grams,2/12/2014
mixed salad,150,grams,26/12/2013
```
## Recipes Format - JSON
```
Array of recipes with format specified as below:
    ￼name : String
    ingredients[]
          item : String 
          amount : int 
          unit : enum
```

Recipes Example:

```json
[ 
  {
    "name": "grilled cheese on toast",
    "ingredients": [
                    { "item":"bread", "amount":"2", "unit":"slices"},
                    { "item":"cheese", "amount":"2", "unit":"slices"}
    ]
  }, 
  {
    "name": "salad sandwich",
    "ingredients": [
                    { "item":"bread", "amount":"2", "unit":"slices"},
                    { "item":"mixed salad", "amount":"100", "unit":"grams"}
    ]
  } 
]
```
