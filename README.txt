
Users and roles:

* users with access to admin pages can modify data (need admin password)
* Member's are created via the admin interface, one or more users (google accounts) are associated with each account. this is done via admin interface
* "regular" members don't have access to the admin interface,
* TODO: different roles could theoretically be assigned via GAE's admin interface, but this is not possible at the moment, see issue http://groups.google.com/group/play-framework/browse_thread/thread/52ce86ffd9d2710a/

Coop and CoopGroup:

* new Coop is created via admin interface, as well as new CoopGroup
* Coop's from the same group will be able to communicate with one another

Sale, Product, BaseProduct:

* BaseProduct could be "lentils", "bread", and such. The current price is associated with the base product, as well as current quantity
* a Product is associated to a BaseProduct and a Sale, as well as a per-sale price
* a Sale is associated to a Coop, and contains a set of Product's
* BaseProduct's are created via admin interface, as well as Sale's - which should have a special page

Order, ProductOrder:

* a ProductOrder is associated to an Order and a Product, and contains the desired Quantity 
* an Order is associated to a Member and a Sale, and contains the order date

Supplier ...
 
