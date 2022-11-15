import pandas
import random


if __name__ == '__main__':

    with open('db.sql', 'w+') as outfile:
        outfile.write(
            """DELETE FROM book;
ALTER TABLE book AUTO_INCREMENT = 1001;

DELETE FROM category;
ALTER TABLE category AUTO_INCREMENT = 1001;

"""
        )
        outfile.write(
            "INSERT INTO `category` (`name`) VALUES ('Classics'),('Fantasy'),('Mystery'),('Romance'),('Crime'),"
            "('History'),('Horror'),('Literature');\n"
        )
        outfile.write("\n")


        df = pandas.read_csv('books.csv', encoding = "ISO-8859-1", skipinitialspace=True)
        df = df.sort_values('category_id')
        for index, row in df.iterrows():
            line = f"INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, " \
                   f"category_id) VALUES ('{row['title']}', '{row['author']}', '{row['description']}', " \
                   f"{int(row['price'])}, " \
                   f"{random.randint(5,10)}, {random.choice([True, False])}, {random.choice([True, False])}, {row['category_id']});\n"
            outfile.write(line)