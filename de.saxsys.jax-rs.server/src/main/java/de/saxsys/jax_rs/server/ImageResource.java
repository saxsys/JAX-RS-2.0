package de.saxsys.jax_rs.server;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public class ImageResource {

	@GET
	@Produces("image/png")
	public Response getImage(@PathParam("id") int userId) {

		Image image;
		try {
			image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("image1.png"));
			if (null != image) {
				final ByteArrayOutputStream out = new ByteArrayOutputStream();
				ImageIO.write((BufferedImage) image, "png", out);
				final byte[] imgData = out.toByteArray();
				final InputStream is = new ByteArrayInputStream(imgData);

				return Response.ok(is).build();
			}
		} catch (IOException e1) {
			return Response.noContent().build();
		}

		return Response.noContent().build();
	}
}
