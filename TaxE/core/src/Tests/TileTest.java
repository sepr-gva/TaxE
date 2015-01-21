package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.seprgva.taxe.Tile;

public class TileTest {

	@Test
	public void test() {
		Tile test = new Tile(238, 238, null, null, false, null);
		assertEquals(238, test.xCoord);
		assertEquals(238, test.yCoord);
	}

}
