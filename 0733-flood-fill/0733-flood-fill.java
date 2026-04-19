class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];
        if (original == color) return image; // no change needed
        dfs(image, sr, sc, original, color);
        return image;
    }

    private void dfs(int[][] image, int i, int j, int original, int color) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length) return;
        if (image[i][j] != original) return;

        image[i][j] = color;
        dfs(image, i + 1, j, original, color);
        dfs(image, i - 1, j, original, color);
        dfs(image, i, j + 1, original, color);
        dfs(image, i, j - 1, original, color);
    }
}
