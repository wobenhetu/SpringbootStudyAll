/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) unsigned NOT NULL ,
  `name` varchar(31) NOT NULL,
  `age` varchar(31) NOT NULL,
  `sex` varchar(31) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

INSERT INTO user(id, name,age,sex) VALUES(1, 'lili','24','women') ON DUPLICATE KEY UPDATE id = id;
INSERT INTO user(id, name,age,sex) VALUES(2, 'dddd','24','women') ON DUPLICATE KEY UPDATE id = id;
INSERT INTO user(id, name,age,sex) VALUES(3, 'vvvv','24','men')ON DUPLICATE KEY UPDATE id = id;
INSERT INTO user(id, name,age,sex) VALUES(4, 'bbbfg','24','women')ON DUPLICATE KEY UPDATE id = id;
INSERT INTO user(id, name,age,sex) VALUES(5, 'yyyyy','24','women')ON DUPLICATE KEY UPDATE id = id;