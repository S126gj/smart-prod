DELIMITER //
CREATE FUNCTION generate_snowflake_id() RETURNS BIGINT
    DETERMINISTIC
    NO SQL
BEGIN
  DECLARE machine_id INT;
  DECLARE datacenter_id INT;
  DECLARE `sequence` BIGINT;
  DECLARE snowflake_id BIGINT;

  -- 设置机器ID和数据中心ID，根据您的需求设置
  SET machine_id = 1; -- 替换为您的机器ID
  SET datacenter_id = 1; -- 替换为您的数据中心ID

  -- 生成当前时间戳（毫秒级）和序列号
  SET sequence = (FLOOR(UNIX_TIMESTAMP(NOW(3)) * 1000) << 22);

  -- 构建雪花ID
  SET snowflake_id = sequence | (datacenter_id << 17) | (machine_id << 12) | (RAND() * 4096) & 4095;

RETURN snowflake_id;
END//
DELIMITER ;