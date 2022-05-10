create database OPS

use OPS

create table Product(
 productId int identity(1,1) primary key ,
 productPrice float,
 productType nvarchar(200)
)

create table Customer(
 customerId int identity(1,1) primary key ,
 customerName nvarchar(200),
 address nvarchar(200),
 phone int
)

create table Orders(
orderId int identity(1,1) primary key ,
customerId int references Customer(customerId),
customerName nvarchar(200),
productId int references Product(productId),
amount float,
orderDate datetime
)
	
create table Stock(
productId int references Product(productId),
quantity int,
shopNo int
)

alter table Orders ADD CONSTRAINT fk_idCA2 FOREIGN KEY(customerId) references Customer(customerId) ;
alter table Orders ADD CONSTRAINT fk_idCA3 FOREIGN KEY(productId) references Product(productId) ;
alter table Stock ADD CONSTRAINT fk_idCA4 FOREIGN KEY(productId) references Product(productId) ;

-- INSERT INTO PRODUCT
INSERT INTO [OPS].[dbo].[Product]([productPrice],[productType])
     VALUES(11111,'Clothes')
GO
INSERT INTO [OPS].[dbo].[Product]([productPrice],[productType])
     VALUES(22222,'Bags')
GO
INSERT INTO [OPS].[dbo].[Product]([productPrice],[productType])
     VALUES(33333,'Shoes')
GO

-- INSERT INTO CUSTOMER
INSERT INTO [OPS].[dbo].[Customer]
           ([customerName],[address],[phone])
     VALUES('Cristiano Ronaldo','Manchester',0987654321)
GO
INSERT INTO [OPS].[dbo].[Customer]
           ([customerName],[address],[phone])
     VALUES('Leoniel Messi','Paris',0987654322)
GO
INSERT INTO [OPS].[dbo].[Customer]
           ([customerName],[address],[phone])
     VALUES('Kylian Mppabe','Paris',0987654323)
GO

-- INSERT INTO ORDERS
INSERT INTO [OPS].[dbo].[Orders]
           ([customerId],[customerName],[productId] ,[amount],[orderDate])
     VALUES
           (1,'Cristiano Ronaldo',1,1,getdate())
GO
INSERT INTO [OPS].[dbo].[Orders]
           ([customerId],[customerName],[productId] ,[amount],[orderDate])
     VALUES
           (1,'Cristiano Ronaldo',2,2,getdate())
GO

INSERT INTO [OPS].[dbo].[Orders]
           ([customerId],[customerName],[productId] ,[amount],[orderDate])
     VALUES
           (2,'Leoniel Messi',3,3,getdate())
GO
INSERT INTO [OPS].[dbo].[Orders]
           ([customerId],[customerName],[productId] ,[amount],[orderDate])
     VALUES
           (2,'Leoniel Messi',2,2,getdate())
GO

INSERT INTO [OPS].[dbo].[Orders]
           ([customerId],[customerName],[productId] ,[amount],[orderDate])
     VALUES
           (3,'Kylian Mppabe',3,2,getdate())
GO