#include<iostream>
#include<string>
#include<fstream>

int main() {
    std::string table = "";
    std::string input = "";
    std::string rename = "";
    std::fstream outFile("rename_cols_query.sql", std::ios::out);

    std::cout<<"Enter name of table to change data from (Enter -1 to exit): ";
    std::cin>>table;
    if(table == "-1") return 0; // break out

    while(true) {
        std::cout<<"Input 'column' name to change (Enter -1 to exit): ";
        std::cin>>input;
        if(input == "-1") {
            break;
        }
        std::cout<<"Input new name of column: ";
        std::cin>>rename;

        // write out new rename query
        outFile<<"EXEC sp_rename '"<<table<<"."<<input<<"', '"<<rename<<"', 'COLUMN';"<<std::endl;
    }
    outFile.close(); // save file
    return 0;
}