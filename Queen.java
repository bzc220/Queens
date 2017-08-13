import java.util.List;

class Queen {
    private int row;
    private int column;

    public Queen(int r) {
        row = r;
        Reset();
    }

    public void Display() {
        System.out.println("(" + row + "," + column + ")");
        return;
    }

    public void Move() {
        column = column + 1;
    }

    /**this checks one queen against one other queen, the lastQueen
     * 
     * @param queen Queen object
     * @return true if no other current queens can eat it
     */
    public boolean Check(Queen queen) {
        if (this.row == queen.row)
            return false;
        if (this.column == queen.column)
            return false;
        if (Math.abs((queen.column - this.column) / (queen.row - this.row)) == 1
                && ((queen.column - this.column) % (queen.row - this.row) == 0))
            return false;
        return true;
    }

    public boolean Validate() {
        if (column >= 8) {
            return false;
        }
        return true;
    }

    public boolean FindPosition(List<Queen> currQueen) throws Exception {
        boolean b = this.Validate();
        /* if b is false, or the piece gets to the end of the board and it still doesn't work, 
            then you gotta move the one from before into a different spot. 
           This is recursive because if that next one goes over the limit, then you need to get rid
            of that queen and keep finding position of the lastqueen */
        if (b == false) {
            int idx = currQueen.size();
            if (idx == 0) {
                throw new Exception();
            } else {
                int before = idx - 1;
                Queen lastQueen = currQueen.get(before);
                currQueen.remove(before);
                lastQueen.FindPosition(currQueen);
                currQueen.add(lastQueen);
                this.Reset();
                this.FindPosition(currQueen);
            }
        } else {
            //else, if there is still room on the board to move, move().
            this.Move();
            if (this.Checks(currQueen)) {
                return true;
            } else {
                this.FindPosition(currQueen);
            }
        }
        return true;
    }

    public void Reset() {
        column = 0;
        return;
    }

    public boolean Checks(List<Queen> queens) {
        for (Queen q : queens) {
            if (this.row > q.row && this.Check(q) == false) {
                return false;
            }
        }
        return true;
    }

}
