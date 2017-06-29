//  Copyright (C) 2016-2017 Dyne.org foundation

//  Sourcecode written and maintained by Denis Roio <jaromil@dyne.org>

//  The use and distribution terms for this software are covered by the
//  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
//  By using this software in any fashion, you are agreeing to be bound by
//  the terms of this license.
//  You must not remove this notice, or any other, from this software.

import java.security.interfaces.RSAPublicKey;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class OpenSSHWriter {

	public byte[] encode(RSAPublicKey key)
		throws IOException
	{
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		byte[] name = "ssh-rsa".getBytes("US-ASCII");
		write(name, buf);
		write(key.getPublicExponent().toByteArray(), buf);
		write(key.getModulus().toByteArray(), buf);
		return buf.toByteArray();
	}

	public void write(byte[] str, OutputStream os)
		throws IOException
	{
		for (int shift = 24; shift >= 0; shift -= 8)
			os.write((str.length >>> shift) & 0xFF);
		os.write(str);
	}

}
