use bookstore;
DROP TABLE IF EXISTS hibernate_sequence;

insert into books value(1, "The Lord of the Rings", "J. R. R. Tolkien", "English", "1954-1955", "150", 1, 100);
insert into books value(2, "Le Petit Prince (The Little Prince)", "Antoine de Saint-Exupéry", "French", "1943", "140", 10, 100);
insert into books value(3, "Harry Potter and the Philosopher's Stone", "J. K. Rowling", "English", "1997", "100", 100, 100);
insert into books value(4, "And Then There Were None", "Agatha Christie", "English", "1939", "100", 1000, 100);
insert into books value(5, "Dream of the Red Chamber", "Cao Xueqin", "Chinese", "1754-1791", "100", 10000, 100);
insert into books value(6, "The Hobbit", "J. R. R. Tolkien", "English", "1937", "100", 100000, 100);
insert into books value(7, "She: A History of Adventure", "H. Rider Haggard", "English", "1887", "100", 1000000, 100);


insert into users values (1, 'Amy', '123', '18217273360', '123@qq.com', 'USER');
insert into users values (2, 'Bob', '456', '18968098851', '321@163.com', 'USER');
insert into users values (3, 'Admin', '888', '13758207880', '888@163.com', 'ADMIN');

