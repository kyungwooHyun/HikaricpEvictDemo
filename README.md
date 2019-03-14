# HikaricpEvictDemo
HikariCP Demo of SoftEviction
```
git clone https://github.com/kyungwooHyun/HikaricpEvictDemo.git
mvn clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```
```
jdbc:sqlserver://localhost;databaseName=master
USE [master]
CREATE TABLE [dbo].[item](
	[id] [int] NULL,
	[name] [nvarchar](max) NOT NULL
)
INSERT INTO item VALUES (1, 'abc');
INSERT INTO item VALUES (2, 'def');
INSERT INTO item VALUES (3, 'ghi');
INSERT INTO item VALUES (4, 'jkl');
```
