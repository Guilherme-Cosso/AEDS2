#include <bits/stdc++.h>

using namespace std;

int main()
{
    int s[10010];
    int numero_de_carr, capacidade, first, last;
    int flag;
    int pos;
   
    while(1)
    {
        cin >> numero_de_carr >> capacidade;
       
        if (!numero_de_carr && !capacidade) return 0;

        flag = 0;
        pos = -1;

        for (int i = 0 ; i < numero_de_carr ; i++)
        {
            cin >> first >> last;
            if (!flag)
            {
                if (pos == -1)
                {
                    pos++;
                    s[pos] = last;
                }
                else
                {
                    int ultimo = s[pos];
                    if (first>= ultimo)
                    {
                        while(pos >= 0 && first >= ultimo)
                        {
                            pos--;
                            if (pos < 0) break;
                            ultimo = s[pos];
                        }
                    }                   
                    if (pos >= capacidade)
                        flag = 1;
                    else
                    {
                        if (pos >= 0  && last > s[pos])
                            flag = 1;
                        else
                        {
                            pos++;
                            s[pos] = last;
                        }
                    }
                }
            }
        }
        if (!flag) cout << "Sim\n";
        else cout << "Nao\n";       
    }
}