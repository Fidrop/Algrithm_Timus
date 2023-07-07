import java.util.*;

public class T1628 {
    static class Cell implements Comparable<Cell> {
        int row;
        int column;

        Cell(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int compareTo(Cell other) {
            if (this.row != other.row) {
                return Integer.compare(this.row, other.row);
            }
            return Integer.compare(this.column, other.column);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        int k = scanner.nextInt();

        Cell[] cells = new Cell[2 * (rows + columns + k)];

        for (int i = 0; i < k; ++i) {
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            cells[i] = new Cell(row, column);
        }

        int index = k;

        for (int i = 1; i <= columns; ++i) {
            cells[index++] = new Cell(0, i);
            cells[index++] = new Cell(rows + 1, i);
        }

        for (int i = 1; i <= rows; ++i) {
            cells[index++] = new Cell(i, 0);
            cells[index++] = new Cell(i, columns + 1);
        }

        int streaks = 0;
        TreeSet<Cell> streakSet = new TreeSet<>();

        Arrays.sort(cells, 0, index);

        for (int i = 0; i + 1 < index; ++i) {
            int diff = cells[i + 1].column - cells[i].column;
            if (cells[i].row == cells[i + 1].row && diff >= 2) {
                if (diff == 2) {
                    streakSet.add(new Cell(cells[i].row, cells[i].column + 1));
                } else {
                    ++streaks;
                }
            }
        }

        Arrays.sort(cells, 0, index, new Comparator<Cell>() {
            public int compare(Cell a, Cell b) {
                if (a.column != b.column) {
                    return Integer.compare(a.column, b.column);
                }
                return Integer.compare(a.row, b.row);
            }
        });

        for (int i = 0; i + 1 < index; ++i) {
            int diff = cells[i + 1].row - cells[i].row;
            if (cells[i].column == cells[i + 1].column && diff >= 2) {
                if (diff == 2) {
                    if (streakSet.contains(new Cell(cells[i].row + 1, cells[i].column))) {
                        ++streaks;
                    }
                } else {
                    ++streaks;
                }
            }
        }

        System.out.println(streaks);
    }
}

