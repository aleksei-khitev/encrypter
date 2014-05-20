/*
 * ru.akhitev.encrypter is a library for encryption.
 * Copyright (c) 2014 Aleksei Khitev (Хитёв Алексей Юрьевич).
 *
 * This file is part of ru.akhitev.encrypter
 *
 * ru.akhitev.encrypter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * ru.akhitev.encrypter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package ru.akhitev.encrypter.file

import org.apache.log4j.Logger
import ru.akhitev.encrypter.Encrypter

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.nio.charset.Charset

/**
 * The class used for encrypting file with AES method
 *
 * @author Aleksei Khitev (alexkhitev@gmail.com)
 */
class AesFileEncrypterImpl implements IFileEncrypter{

    /**
     * The constant used for storing encryption method for key specification
     */
    static final String CRYPTION_METHOD = "AES"

    /**
     * The constant used for storing encryption method for chipher specification
     */
    static final String CHIPER_CRYPTION_METHOD = "AES/CBC/PKCS5Padding"

    /**
     * The constant used for storing encryption key's charset
     */
    static final String KEY_CHARSET = "windows-1251"

    /**
     * The constant used for storing encryption key's length
     */
    static final int BYTE_ARRAY_LENGTH = 16

    /**
     * Encryption key
     */
    String encryptionKey

    /**
     * Input file
     */
    File inputFile

    /**
     * Output file
     */
    File outputFile

    /**
     * The method used for encryption file
     */
    void encryptFile() {
        byte[] inputBytes = readBytesFromFile() // Reading file to the byte array
        // Creating an encrypter
        Cipher cipher = Cipher.getInstance(CHIPER_CRYPTION_METHOD)
        cipher.init(Cipher.ENCRYPT_MODE, prepareCryptoKey(), new IvParameterSpec(new byte[BYTE_ARRAY_LENGTH]))
        writeBytesToFile(cipher.doFinal(inputBytes)) // writing byte array to the file
    }

    /**
     * The method used for decryption file
     */
    void decryptFile() {
        byte[] inputBytes = readBytesFromFile() // Reading file to the byte array
        // Creating an encrypter
        Cipher cipher = Cipher.getInstance(CHIPER_CRYPTION_METHOD);
        cipher.init(Cipher.DECRYPT_MODE, prepareCryptoKey(), new IvParameterSpec(new byte[BYTE_ARRAY_LENGTH]));
        writeBytesToFile(cipher.doFinal(inputBytes)) // writing byte array to the file
    }

    /**
     * The method used for preparing encryption key
     * @return Secret key
     */
    SecretKeySpec prepareCryptoKey(){
        byte[] raw = encryptionKey.getBytes(Charset.forName(KEY_CHARSET));
        if (raw.length != BYTE_ARRAY_LENGTH) {
            throw new IllegalArgumentException("Invalid key size.");
        }
        SecretKeySpec skeySpec = new SecretKeySpec(raw, CRYPTION_METHOD);
        return skeySpec
    }

    /**
     * The method used for reading file to the byte array
     */
    Byte[] readBytesFromFile(){
        ByteArrayOutputStream ous = null
        InputStream ios = null
        try {
            byte[] buffer = new byte[4096]
            ous = new ByteArrayOutputStream()
            ios = new FileInputStream(inputFile)
            int read = 0
            while ((read = ios.read(buffer)) != -1)
                ous.write(buffer, 0, read)
        } finally {
            try {
                if (ous != null)
                    ous.close()
            } catch (IOException e) {
                Encrypter.logger.error("Error in AesFileEncrypterImpl:\n ${e}")
            }
            try {
                if (ios != null)
                    ios.close()
            } catch (IOException e) {
                Encrypter.logger.error("Error in AesFileEncrypterImpl:\n ${e}")
            }
        }
        return ous.toByteArray()
    }

    /**
     * The method used for writing byte array to the file
     * @param bytes Byte Array
     */
    void writeBytesToFile(byte[] bytes){
        FileOutputStream out = new FileOutputStream(outputFile);
        out.write(bytes);
    }
}
