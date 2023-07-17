#include <iostream>
#include <vector>
#include <queue>
using namespace std;

bool visit[101][101];
int v[101][101];
int dr[4] = { 1, 0, -1, 0 };
int dc[4] = { 0, 1, 0, -1 };
int n, m;

void bfs(int r, int c) {
	queue<pair<int, int>> q;
	q.push({r, c});
	visit[r][c] = 1;

	while (!q.empty()) {
		int r = q.front().first;
		int c = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int rr = r + dr[i];
			int cc = c + dc[i];

			if (rr < 0 || cc < 0 || rr >= n || cc >= m) continue;
			if (v[rr][cc] == 0 || visit[rr][cc]) continue;
            
            v[rr][cc] = v[r][c] + 1;
            visit[rr][cc] = 1;
            q.push({rr, cc});
		}
	}
}

int main() {
	cin >> n >> m;
	cin.ignore();
	for (int i = 0; i < n; i++) {
		string s;	cin >> s;
		for (int j = 0; j < m; j++) 
			v[i][j] = s[j] - '0';
	}

	bfs(0, 0);
	cout << v[n - 1][m - 1];
}