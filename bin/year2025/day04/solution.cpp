#include <fstream>
#include <vector>
#include <string>

#include <iostream>
using namespace std;

void part1(vector<string> grid, vector<pair<int,int>> directions){
  auto grid_get = [&grid](int i, int j) -> char {
    if ( i < 0 || i >= grid.size()) return 0;
    if ( j < 0 || j >= grid[i].size()) return 0;
    return grid[i][j];
  };

  int total = 0;
  for (int i = 0; i < grid.size(); ++i){
    for (int j = 0; j < grid[i].size(); ++j){
      if (grid[i][j] != '@') continue;

      int count = 0;
      for (auto d : directions){
        if (grid_get(i + d.first, j + d.second) == '@'){
          count++;
          if (count >= 4)
            break;
        }
      }

      if (count < 4)
        total += 1;
    }
  }
  std::cout << "part 1: " << total << std::endl;
}

void part2(vector<string> grid, vector<pair<int,int>> directions){
  auto grid_get = [&grid](int i, int j) -> char {
    if ( i < 0 || i >= grid.size()) return 0;
    if ( j < 0 || j >= grid[i].size()) return 0;
    return grid[i][j];
  };

  int total = 0;
  bool can_go = true;
  while (can_go){
    can_go = false;

    for (int i = 0; i < grid.size(); ++i){
      for (int j = 0; j < grid[i].size(); ++j){
        if (grid[i][j] != '@') continue;

        int count = 0;
        for (auto d : directions){
          if (grid_get(i + d.first, j + d.second) == '@'){
            count++;
            if (count >= 4)
              break;
          }
        }

        if (count < 4){
          can_go = true;
          total += 1;
          grid[i][j] = '.';
        }

      }
    }
  }
  std::cout << "part 2: " << total << std::endl;
}

int main(){
  std::ifstream file("input.txt");
  std::vector<std::string> grid;
  std::vector<std::pair<int, int>> directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

  std::string row;
  while (std::getline(file, row)) grid.push_back(row);

  part1(grid, directions);
  part2(grid, directions);

  return 0;
}
