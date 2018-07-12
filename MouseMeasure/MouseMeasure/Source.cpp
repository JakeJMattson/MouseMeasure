#include <iostream>
#include <Windows.h>

using std::cout;
using std::endl;

int main()
{
	while (true)
	{
		POINT start = POINT(), previous = POINT();

		//Reset start when mouse is released
		bool shouldStart = true;

		while (GetKeyState(VK_LBUTTON) < 0)
		{
			if (shouldStart)
			{
				GetCursorPos(&start);
				shouldStart = false;
			}

			//Current cursor position
			POINT current;
			GetCursorPos(&current);

			if (!(previous.x == current.x && previous.y == current.y))
			{
				//Calculate cursor position differences
				int height = abs(start.y - current.y);
				int width = abs(start.x - current.x);

				//Clear console
				system("CLS");

				//Display results
				cout << "Start   - x: " << start.x << " y: " << start.y << endl;
				cout << "Current - x: " << current.x << " y: " << current.y << endl;
				cout << "Delta width: " << width << endl;
				cout << "Delta height: " << height << endl;
			}

			//Store location to prevent unnecessary computations
			previous = current;
		}
	}

	return 0;
}