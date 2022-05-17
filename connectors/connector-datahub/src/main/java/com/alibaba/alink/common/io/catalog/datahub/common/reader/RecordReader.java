/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.alink.common.io.catalog.datahub.common.reader;

import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.core.io.InputSplit;

import com.alibaba.alink.common.io.catalog.datahub.common.source.WatermarkProvider;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by sleepy on 16/1/11.
 *
 * @param <OUT>    the type parameter
 * @param <CURSOR> the type parameter
 */
public interface RecordReader<OUT, CURSOR extends Serializable> extends WatermarkProvider {
	/**
	 * Open.
	 *
	 * @param split the split
	 * @throws IOException the io exception
	 */
	void open(InputSplit split, RuntimeContext context) throws IOException;

	/**
	 * Next boolean.
	 *
	 * @return the boolean
	 * @throws IOException          the io exception
	 * @throws InterruptedException the interrupted exception
	 */
	boolean next() throws IOException, InterruptedException;

	/**
	 * Gets message.
	 *
	 * @return the message
	 */
	OUT getMessage();

	/**
	 * Close.
	 *
	 * @throws IOException the io exception
	 */
	void close() throws IOException;

	/**
	 * Seek.
	 *
	 * @param cursor the cursor
	 * @throws IOException the io exception
	 */
	void seek(CURSOR cursor) throws IOException;

	/**
	 * Gets progress.
	 *
	 * @return the progress
	 * @throws IOException the io exception
	 */
	CURSOR getProgress() throws IOException;

	/**
	 * Get message delay (millseconds).
	 *
	 * @return delay
	 */
	long getDelay();

	/**
	 * Get message delay (millseconds) from being fetched.
	 *
	 * @return delay
	 */
	long getFetchedDelay();

	/**
	 * Check if current record is heartbeat, heartbeat can only update watermark.
	 *
	 * @return
	 */
	boolean isHeartBeat();
}