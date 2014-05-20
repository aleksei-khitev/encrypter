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

package ru.akhitev.encrypter.fileEcryption

import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import ru.akhitev.encrypter.Encrypter
import ru.akhitev.encrypter.file.AesFileEncrypterImpl
import ru.akhitev.encrypter.file.IFileEncrypter

/**
 * The class used for testing AesFileEncrypterImpl
 *
 * @author Aleksei Khitev (alexkhitev@gmail.com)
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AesFileEncrypterImplTest {
    File inputFile = null
    File encryptedFile = null
    File decryptedFile = null
    String key = ""

    /**
     * The method used for initialising variables for testing
     */
    @Before
    void init(){
        key = "ThisIsASecretKey"
        inputFile = new File("test/input.txt")
        encryptedFile = new File("test/encrypted.kvz")
        decryptedFile = new File("test/decrypted.txt")
    }

    /**
     * The method used for testing encryption method
     */
    @Test
    void firstTest(){
        encryptedFile.delete()
        IFileEncrypter coder = new AesFileEncrypterImpl()
        coder.setEncryptionKey(key)
        coder.inputFile=inputFile
        coder.outputFile=encryptedFile
        coder.encryptFile()
        assert encryptedFile.exists()
    }

    /**
     * The method used for testing decryption method
     */
    @Test
    void secondTest(){
        decryptedFile.delete()
        IFileEncrypter coder = new AesFileEncrypterImpl()
        coder.setEncryptionKey(key)
        coder.inputFile=encryptedFile
        coder.outputFile=decryptedFile
        coder.decryptFile()
        assert decryptedFile.exists()
    }

    /**
     * The method used for testing manager
     */
    @Test
    void thirdTest(){
        encryptedFile.delete()
        decryptedFile.delete()
        Encrypter.encryptFileWithAes(inputFile,encryptedFile,key)
        encryptedFile.exists()
        Encrypter.decryptFileWithAes(encryptedFile,decryptedFile,key)
        decryptedFile.exists()
    }

}