create table Users(
 UserID char(9) primary key not null,
 UserFullName varchar(50) not null,
 UserEmail varchar(50) not null,
 UserPhone varchar(12) not null,
 UserPassword varchar(50) not null,
 UserGender varchar(10) not null,
 UserAddress varchar(100) not null,
 UserRole varchar(10) not null
);

create table Fish(
 FishID char(9) primary key not null,
 FishName varchar(25) not null,
 FishType varchar(15) not null,
 FishPrice int not null,
 FishStock int not null
);

create table Cart(
 UserID char(9) not null references User(UserID) on delete cascade on update cascade,
 FishID char(9) not null references Fish(FishID) on delete cascade on update cascade,
 Quantity int not null,
 primary key(UserID, FishID)
);

create table HeaderTransaction (
 TransactionID char(5) primary key not null,
 UserID char(9) not null references User(UserID) on delete cascade on update cascade,
 TransactionDate date not null
);

create table DetailTransaction(
 TransactionID char(5) not null references HeaderTransaction(TransactionID) on delete cascade on update cascade,
 FishID char(9) not null references Fish(FishID) on delete cascade on update cascade,
 Quantity int not null,
 primary key(TransactionID, FishID)
);

insert into Users values
('US-HMS123','Hubert Michael Sanyoto', 'hms@gmail.com', '080123123123', 'hms12345', 'Male', 'Rambutan Street', 'User'),
('US-ARW001','Andrew Reyner Wibowo', 'arw@blitz.com', '081134126173', 'arw12345', 'Male', 'Mango Street', 'Admin'),
('US-SKB019','Sandyka Bala', 'skb@yahoo.com', '085924133529', 'skb12345', 'Male', 'Durian Street', 'User'),
('US-EIA252','Inge Intania', 'eia@gmail.com', '088623188177', 'eia12345', 'Female', 'Lechy Street', 'User');

insert into Fish values
('FH-C1U8P4', 'Black Goldfish', 'Freshwater', 50000, 10),
('FH-K4G13H', 'Red Goldfish', 'Freshwater', 40000, 20),
('FH-R2A7K0', 'Rainbow Guppy', 'Freshwater', 10000, 50),
('FH-BS092L', 'Sea Bass', 'Seawater', 35000, 100),
('FH-LOB573', 'Lobster', 'Seawater', 120000, 7),
('FH-F7MI01', 'Milkfish', 'Brackish', 56000, 70),
('FH-M30WFI', 'Catfish', 'Brackish', 15000, 150);