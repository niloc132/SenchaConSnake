package net.snake.client.widget;

import java.util.ArrayList;
import java.util.List;

import net.snake.shared.models.Arena;
import net.snake.shared.models.Cell;
import net.snake.shared.models.Snake;

import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.DrawComponent;
import com.sencha.gxt.chart.client.draw.sprite.CircleSprite;
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

    private final DrawComponent drawComponent = new DrawComponent();

    private Point origin = new Point(0, 0);

    private int scale;

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
    }

    private void updateDimensions(final int width, final int height)
    {
        scale = Math.min(width, height);
        origin = new Point((width - scale) / 2, (height - scale) / 2);
    }

    public void draw(final Arena arena)
    {
        final List<Sprite> old = sprites;
        sprites = new ArrayList<Sprite>();

        for (final Cell food : arena.getFood())
        {
            draw(food, FOOD_COLOR);
        }

        for (final Snake snake : arena.getSnakes())
        {
            for (final Cell snakeCell : snake.getCells())
            {
                draw(snakeCell, SNAKE_COLOR);
            }
        }

        for (final Sprite oldSprite : old)
        {
            drawComponent.remove(oldSprite);
        }

        drawComponent.redraw();
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
}
