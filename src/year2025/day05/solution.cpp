#include <iostream>
#include <fstream>
#include <vector>
#include <string>

#include <algorithm>

using namespace std;

struct range {
  long start, end;
};

void part1(vector<range> ranges, vector<int> nums){
  long total = 0;
  for (long num : nums){
    for (auto &r : ranges){
      if (r.start > num) {
        break;
      }
      if (r.end >= num) {
        total += 1;
        break;
      }
    }
  }
  cout << "part1: " << total << std::endl;
}

void part2(vector<range> ranges){
  vector<range> pools;
  range cur_pool = ranges[0];
  
  for (auto &range : ranges){
    if (cur_pool.end >= range.start){
      cur_pool.end = max(cur_pool.end, range.end);
    }
    else {
      pools.push_back(cur_pool);
      cur_pool = range;
    }
  }
  pools.push_back(cur_pool);

  long total = 0;
  for (auto &pool : pools){
    std::cout << "{" << pool.start << ", " << pool.end << "}\n";
    total += pool.end - pool.start + 1;
  }

  std::cout << "part2: " << total << std::endl;

}

int main(){
  ifstream file("input.txt");
  vector<range> ranges;
  vector<long>  nums;

  string ln;
  while (getline(file, ln)){
    if (ln.empty()) break;

    size_t pos = ln.find("-");
    ranges.push_back({stoll(ln.c_str()), stoll(ln.c_str() + pos + 1)});
  }

  while (getline(file, ln)) nums.push_back(stol(ln));

  auto range_cmp = [](const range &a, const range &b){
    if (a.start == b.start)
      return a.end > b.end;
    return a.start < b.start;
  };
  sort(ranges.begin(), ranges.end(), range_cmp);

  //for (range &r: ranges){
  //  std::cout << "{" << r.start << ", " << r.end << "}\n";
  //}
  //

  part2(ranges);

}

