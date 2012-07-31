package cc.example.awt;

/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cc.adjusting.ChineseCharacterTypeAdjuster;
import cc.adjusting.piece.ShapeInformation;
import cc.adjusting.piece.SimplePieceAdjuster;
import cc.core.ChineseCharacter;
import cc.core.ChineseCharacterUtility;
import cc.moveable_type.ChineseCharacterMovableType;
import cc.moveable_type.piece.PieceMovableType;
import cc.printing.ChineseCharacterTypePrinter;
import cc.printing.awt.piece.AwtForPiecePrinter;
import cc.setting.ChineseCharacterTypeSetter;
import cc.setting.piece.SimplePieceSetter;

/** A demonstration of writing custom Stroke classes */
public class AwtTypesettingTest extends JPanel
{
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 1420, HEIGHT = 1050; // Size of our example
	static final int TYPE_SIZE = 200;
	static final int LINE_SIZE = 4;
	private String word = /* "⿰禾火秋"; // */"秋漿國一" + "⿰禾火⿱將水⿴囗或二"
			+ "⿱⿰⿰糹言糹攵⿰矛⿱攵力⿱木⿰木木三" + "變務森四" + "攵力木五";// */;
	static final String SUNG_FONT = "全字庫正宋體";
	static final String KAI_FONT = "全字庫正楷體";
	static final String BLACK_FONT = "文泉驛正黑";
	static final String 文鼎中圓 = "文鼎中圓";
	static private final String FontName = SUNG_FONT;
	private int FontStyle = Font.BOLD;

	public String getName()
	{
		return "Custom Strokes";
	}

	public int getWidth()
	{
		return WIDTH;
	}

	public int getHeight()
	{
		return HEIGHT;
	}

	/** Draw the example */
	public void paint(Graphics g1)
	{
		Graphics2D g = (Graphics2D) g1;
		final int TEST = 144;
		Font f = new Font("全字庫正宋體", Font.BOLD, TEST);
		GlyphVector gv = f
				.createGlyphVector(((Graphics2D) g1).getFontRenderContext(),
						"一二三變務森國a,龜龘"/* ⿻ab!" */);
		// System.out.println(gv.getNumGlyphs());
		// Shape shape = gv.getGlyphLogicalBounds(0);
		System.out.println(gv.getGlyphLogicalBounds(0));
		System.out.println(gv.getGlyphOutline(0).getBounds());
		Rectangle2D.Double lDouble = new Rectangle2D.Double(0, 0, 1000,1 );

		g.setStroke(new NullStroke());
		g.translate(0, 144);
		g.setColor(Color.RED);
		g.draw(lDouble);
		for (int i = 0; i < gv.getNumGlyphs(); ++i)
		{
			g.translate(1, 0);
			g.setColor(Color.GRAY);
			g.draw(gv.getGlyphLogicalBounds(i));
			g.setColor(Color.LIGHT_GRAY);
			g.draw(gv.getGlyphVisualBounds(i));
			g.setColor(Color.BLACK);
			g.draw(gv.getGlyphOutline(i));// TODO

			System.out.println((new Area(gv.getGlyphLogicalBounds(i)))
					.getBounds2D());
			System.out.println((new Area(gv.getGlyphVisualBounds(i)))
					.getBounds2D());
			System.out.println((new Area(gv.getGlyphVisualBounds(i)))
					.getBounds2D().getY()+(new Area(gv.getGlyphVisualBounds(i)))
					.getBounds2D().getHeight()/2);
			// System.out.println(gv.getGlyphPosition(i));
			ShapeInformation shapeInformation = new ShapeInformation(new Area(
					gv.getGlyphOutline(i)));
			System.out.println(i + "="
					+ shapeInformation.getApproximativeRegion()
					/ shapeInformation.getApproximativeCircumference() / TEST
					+ "=" + shapeInformation.getApproximativeRegion() + "/"
					+ shapeInformation.getApproximativeCircumference() + "/"
					+ TEST);
		}

		return;
	}

	public static void main(String[] a)
	{
		JFrame f = new JFrame();
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		f.setContentPane(new AwtTypesettingTest());
		f.setSize(WIDTH, HEIGHT);
		f.setVisible(true);
	}

}
