import java.util.Stack;

class MinStack {
    private Stack<Integer> mainStack;
    private int min;

    public MinStack() {
        mainStack = new Stack<Integer>();
        min = Integer.MAX_VALUE;
    }

    public void push(int val) {
        mainStack.push(val);
        min = Math.min(val, min);
    }

    public void pop() {
        int v = mainStack.pop();
        if (mainStack.isEmpty()) {
            this.min = Integer.MAX_VALUE;
            return;
        }
        if (this.min == v) {
            int newMin = mainStack.peek();
            for (int s : mainStack) {
                newMin = Math.min(newMin, s);
            }
            this.min = newMin;
        }
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        return this.min;
    }
}