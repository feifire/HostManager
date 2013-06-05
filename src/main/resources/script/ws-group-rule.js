if (wsType == 'WorkSheet') {
    log.debug('found type[WorkSheet].');
    log.debug(worksheet.type);
    if (worksheet.type == 'TradeComplaint') {
        result = worksheet.orderId == null ? 'TradeComplaintOffline' : 'TradeComplaintOnline';
    } else if (worksheet.type == 'TradeCheat') {
        result = worksheet.orderId == null ? 'TradeCheatOffline' : 'TradeCheatOnline';
    }
} else if (wsType == 'OtherWorkSheet') {
    log.debug('found type[OtherWorkSheet].');
    log.debug(worksheet.refundId);
    result = 'RefundComplaint';
}