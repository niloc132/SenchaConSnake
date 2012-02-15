package net.snake.client.widget;

import java.util.ArrayList;
import java.util.List;

import net.snake.shared.models.Arena;
import net.snake.shared.models.Cell;
import net.snake.shared.models.Snake;

import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.DrawComponent;
import com.sencha.gxt.chart.client.draw.sprite.CircleSprite;
import com.sencha.gxt.chart.client.draw.sprite.RectangleSprite;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.widget.core.client.Composite;

/**
 * @author Richard Truong <ayuforever@gmail.com>
 */
public class ArenaView extends Composite
{
    private static final Color FOOD_COLOR = new Color("#79A933");

    private static final Color SNAKE_COLOR = new Color("#888888");

    private static final Color MY_SNAKE_COLOR = new Color("#FF0000");

    private static final Color WALL_COLOR = new Color("#000000");

    private final DrawComponent drawComponent = new DrawComponent();

    private Point origin = new Point(0, 0);

    private int scale;

    private int width;

    private int height;

    private Arena arena;

    private List<Sprite> sprites = new ArrayList<Sprite>();

    public ArenaView()
    {
        initWidget(drawComponent);
    }

    @Override
    protected void onResize(final int width, final int height)
    {
        updateDimensions(width, height);
        super.onResize(width, height);
        redraw();
    }

    private void updateDimensions(final int width, final int height)
    {
        this.width = width;
        this.height = height;
        scale = Math.min(width, height);
        origin = new Point((width - scale) / 2, (height - scale) / 2);
    }

    public void draw(final Arena arena)
    {
        this.arena = arena;

        final List<Sprite> old = sprites;
        sprites = new ArrayList<Sprite>();

        for (final Cell food : arena.getFood())
        {
            draw(food, FOOD_COLOR);
        }

        for (final Snake snake : arena.getSnakes())
        {
            if (snake.isAlive()) {
                for (final Cell snakeCell : snake.getCells())
                {
                    draw(snakeCell, SNAKE_COLOR);
                }
            }
        }

        for (final Sprite oldSprite : old)
        {
            drawComponent.remove(oldSprite);
        }

        drawWalls();

        drawComponent.redrawSurfaceForced();
    }


    private void draw(final Cell cell, final Color color)
    {
        final CircleSprite circle = new CircleSprite();
        circle.setCenterX(cell.getX() * scale + origin.getX());
        circle.setCenterY(cell.getY() * scale + origin.getY());
        circle.setRadius((cell.getWidth() * scale) / 2);
        circle.setFill(color);

        drawComponent.addSprite(circle);

        sprites.add(circle);
    }

    private void drawWalls()
    {
        if (scale == height)
            drawHorizontalWalls();
        else
            drawVerticalWalls();
    }

    private void drawHorizontalWalls()
    {
        final RectangleSprite east = new RectangleSprite((width - scale) / 2, height, origin.getX() + scale, 0);
        east.setFill(WALL_COLOR);
        drawComponent.addSprite(east);
        sprites.add(east);

        final RectangleSprite west = new RectangleSprite((width - scale) / 2, height, 0, 0);
        west.setFill(WALL_COLOR);
        drawComponent.addSprite(west);
        sprites.add(west);
    }

    private void drawVerticalWalls()
    {
        final RectangleSprite north = new RectangleSprite(width, (height - scale) / 2, 0, 0);
        north.setFill(WALL_COLOR);
        drawComponent.addSprite(north);
        sprites.add(north);

        final RectangleSprite south = new RectangleSprite(width, (height - scale) / 2, 0, origin.getY() + scale);
        south.setFill(WALL_COLOR);
        drawComponent.addSprite(south);
        sprites.add(south);
    }

    private void redraw()
    {
        draw(arena);
    }
}
