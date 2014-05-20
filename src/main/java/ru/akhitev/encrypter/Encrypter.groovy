/*
 * ru.akhitev.encrypter is a library for encryption.
 * Copyright (c) 2014 Aleksei Khitevi (Хитёв Алексей Юрьевич).
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

package ru.akhitev.encrypter

import org.apache.log4j.Logger
import ru.akhitev.encrypter.file.AesFileEncrypterImpl
import ru.akhitev.encrypter.file.IFileEncrypter

/**
 * The class used for managing interfaces and implementations
 * for encryption and decryption
 *
 * @author Aleksei Khitev (alexkhitev@gmail.com)
 */
class Encrypter{
    static Logger logger

    public static encryptFileWithAes(File inputFile, File outputFile, String encryptionKey){
        IFileEncrypter fileEncrypter = new AesFileEncrypterImpl(inputFile: inputFile, outputFile: outputFile, encryptionKey: encryptionKey)
        fileEncrypter.encryptFile()
    }

    public static decryptFileWithAes(File inputFile, File outputFile, String encryptionKey){
        IFileEncrypter fileDecrypter = new AesFileEncrypterImpl(inputFile: inputFile, outputFile: outputFile, encryptionKey: encryptionKey)
        fileDecrypter.decryptFile()
    }
}